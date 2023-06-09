package com.waa.PropertyManagment.service.impl;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.waa.PropertyManagment.entity.Offer;
import com.waa.PropertyManagment.entity.Property;
import com.waa.PropertyManagment.entity.Status;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.enums.OfferStatus;
import com.waa.PropertyManagment.enums.Roles;
import com.waa.PropertyManagment.repository.OfferRepository;
import com.waa.PropertyManagment.repository.PropertyRepository;
import com.waa.PropertyManagment.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final PropertyRepository propertyRepository;
    private final  UserRepository userRepository;

    public OfferService(OfferRepository offerRepository, PropertyRepository propertyRepository, UserRepository userRepository){
        this.offerRepository=offerRepository;
        this.propertyRepository=propertyRepository;
        this.userRepository=userRepository;
    }
    public List<Offer> findByCustomerId(Long customerId) {
        return offerRepository.findByUserId(customerId);
    }
    public List<Offer> findActiveOffersByCustomerId(Long customerId){
        User customer= userRepository.findById(customerId).get();
      if (customer.getRole().equals(Roles.CUSTOMER)) {
          return offerRepository.findActiveOffersByCustomerId(customerId);
      }
        System.out.println("you don't have CUSTOMER acess");
      return null;

    }


    public List<Offer> findActiveOffersByOwnerId(Long OwnerId){
        User customer= userRepository.findById(OwnerId).get();
        if (customer.getRole().equals(Roles.OWNER)) {
            return offerRepository.findActiveOffersByCustomerId(OwnerId);
        }
        System.out.println("You don't have OWNER access");
        return null;
    }




    public List<Offer> findAllActiveOffers() {
        return offerRepository.findByStatus(Status.PENDING);
    }


    public List<Property> findActiveOffersProperties(Long customerId){
     List<Offer> offers=findActiveOffersByCustomerId(customerId);
        ArrayList<Property>properties = new ArrayList<>();
        for (Offer offer : offers) {
            properties.add(offer.getProperty());
        }
     return properties;
    }

    public List<Property> findActiveOffersPropertiesForOwner(Long ownerId){
        // return properties associated with specific offer
        List<Offer>offers= findActiveOffersByOwnerId(ownerId);
        ArrayList<Property>properties = new ArrayList<>();
        for (Offer offer : offers) {
            properties.add(offer.getProperty());

        }
        return properties;
    }

    public List<User> findActiveOfferPropertiesCustomer(Long ownerId){
        List<Offer> offers =findActiveOffersByOwnerId(ownerId);
        ArrayList<User> users =new ArrayList<>();
        for (Offer offer:offers){
            users.add(offer.getUser());
        }
        return users;
    }

    public  List<Offer> findOffersByPropertyId(Long propertyId){
        return offerRepository.findByProperty_Id(propertyId);
    }




    public void rejectOffer(Long offerId) throws ChangeSetPersister.NotFoundException {
        Offer offer = offerRepository.findById(offerId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        offer.setStatus(OfferStatus.REJECTED);
        offer.getProperty().setStatus(Status.AVAILABLE);
        offerRepository.save(offer);
    }
    public Offer acceptOffer(Long offerId) throws ChangeSetPersister.NotFoundException {
        Offer offer =offerRepository.findById(offerId).orElseThrow((ChangeSetPersister.NotFoundException::new));
        offer.setStatus(OfferStatus.ACCEPTED);
        offer.getProperty().setStatus(Status.CONTINGENT);
        offerRepository.save(offer);
        return offer;
    }

    /*c.	Cannot cancel offer after 'contingency':
      When implementing the cancel offer functionality,
      add a condition to check if the offer is still
      within the contingency period. If it's not,
       display an error message or disable the cancel option.
     */

    public boolean canCancelOffer(Long offerId){
        Optional<Offer> offerOptional= offerRepository.findById(offerId);
        if(offerOptional.isPresent()){
            Offer offer=offerOptional.get();
            Property property= propertyRepository.findById(offer.getProperty().getId()).orElse(null);
            if(property!=null){
                return !offer.getStatus().equals(Status.CONTINGENT);
            }
        }
        return  false;
    }
    public void cancelOffer(Long offerId){
        Optional<Offer> offerOptional =offerRepository.findById(offerId);

        if (offerOptional.isPresent()){
            Offer offer= offerOptional.get();
            Property property=propertyRepository.findById(offer.getProperty().getId()).orElse(null);

            if(property!=null){
                property.setStatus(Status.AVAILABLE);
                propertyRepository.save(property);
            }
        }
    }

    /*
    * d.	Download/Print receipt as PDF or Excel:
        Create an endpoint to generate receipts for the
        * customer's offers. You can use libraries like
        * Apache POI for Excel files or iText for PDF
        * files to create and export the receipt documents.
    * */

    public ByteArrayInputStream generateReceipt(Long offerId) throws DocumentException {
        Optional<Offer> offerOptional =offerRepository.findById(offerId);
        if(offerOptional.isPresent()){
            Offer offer=offerOptional.get();
            ByteArrayOutputStream out =new ByteArrayOutputStream();

            Document document= new Document();
            PdfWriter.getInstance(document,out);
            document.open();
            //Add Offer details to the document
            Font font=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
            Paragraph paragraph=new Paragraph("Offer ID: "+offer.getId(),font);
            Paragraph paragraph1=new Paragraph("Offered By: "+offer.getUser().getName());
            Paragraph paragraph2=new Paragraph("Property Name: "+offer.getProperty().getPropertyName(),font);
            Paragraph paragraph3=new Paragraph("Property Price: "+offer.getProperty().getPrice(),font);
            Paragraph paragraph4=new Paragraph("Offered Amount: "+offer.getAmount(),font);
            document.add(paragraph1);document.add(paragraph2);document.add(paragraph3);
            document.add(paragraph4);
            document.close();
            // Return the generated PDF as a byte array input stream
            return new ByteArrayInputStream(out.toByteArray());
        }
        else{
            return null;
        }

    }
}
