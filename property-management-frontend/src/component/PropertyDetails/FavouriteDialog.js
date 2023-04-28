import React, { useState } from "react";
import {
  Dialog,
  DialogTitle,
  DialogActions,
  DialogContent,
  Button,
  TextField,
  List,
  ListItem,
  ListItemText,
} from "@mui/material";

const FavouriteDialog = (props) => {
  const [selectedList, setSelectedList] = useState(null);
  const [newListName, setNewListName] = useState("");

  const lists = [{ id: 1, name: "NY" }];

  const handleAddToList = () => {
    if (selectedList) {
      console.log(selectedList.id);
    } else if (newListName) {
      console.log(newListName);
      setNewListName("");
    }
  };

  const handleListClick = (list) => {
    setSelectedList(list);
    props.toggle();
  };

  return (
    <Dialog open={props.open} onClose={props.toggle}>
      <DialogTitle>Favorite Lists</DialogTitle>
      <DialogContent>
        <List>
          {lists.map((list) => (
            <ListItem
              key={list.id}
              button
              onClick={() => handleListClick(list)}
              selected={selectedList && selectedList.id === list.id}
            >
              <ListItemText primary={list.name} />
            </ListItem>
          ))}
        </List>
        <TextField
          label="New List Name"
          value={newListName}
          onChange={(e) => setNewListName(e.target.value)}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={props.toggle}>Cancel</Button>
        <Button onClick={handleAddToList}>Add to List</Button>
      </DialogActions>
    </Dialog>
  );
};

export default FavouriteDialog;
