import { Typography } from '@mui/material'
import React from 'react'
import { useParams } from 'react-router'

const PropertyDetails = () => {

    const params = useParams();
  return (
    <div>
        <Typography>PropertyDetails: {params.id}</Typography>
    </div>
  )
}

export default PropertyDetails