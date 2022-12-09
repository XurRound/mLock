package me.xurround.mlock.layout.components;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class AutocompletionTextField extends TextField
{
    private final SortedSet<String> entries;
    private final ContextMenu entriesPopup;

    public AutocompletionTextField()
    {
        super();
        entries = new TreeSet<>();
        entriesPopup = new ContextMenu();

        addEventFilter(KeyEvent.KEY_PRESSED, e ->
        {
            if(e.getCode() == KeyCode.DOWN)
            {
                if(!entriesPopup.isShowing())
                    entriesPopup.show(AutocompletionTextField.this, Side.BOTTOM, 0, 0);
                entriesPopup.requestFocus();
            }
        });

        textProperty().addListener((observable, oldValue, newValue) ->
        {
            String enteredText = getText();
            if (enteredText == null || enteredText.isEmpty())
                entriesPopup.hide();
            else
            {
                List<String> filteredEntries = entries.stream()
                    .filter(e -> e.toLowerCase().contains(enteredText.toLowerCase()))
                    .collect(Collectors.toList());
                if (!filteredEntries.isEmpty())
                {
                    populatePopup(filteredEntries, enteredText);
                    if (!entriesPopup.isShowing())
                        entriesPopup.show(AutocompletionTextField.this, Side.BOTTOM, 0, 0);
                }
                else
                    entriesPopup.hide();
            }
        });
        focusedProperty().addListener((observableValue, oldValue, newValue) -> entriesPopup.hide());
    }

    private static TextFlow buildTextFlow(String text, String filter)
    {
        int filterIndex = text.toLowerCase().indexOf(filter.toLowerCase());
        Text textBefore = new Text(text.substring(0, filterIndex));
        Text textAfter = new Text(text.substring(filterIndex + filter.length()));
        Text textFilter = new Text(text.substring(filterIndex,  filterIndex + filter.length()));
        textFilter.setFill(Color.ORANGE);
        return new TextFlow(textBefore, textFilter, textAfter);
    }

    private void populatePopup(List<String> searchResult, String searchRequest)
    {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++)
        {
            final String result = searchResult.get(i);
            Label entryLabel = new Label();
            entryLabel.setGraphic(buildTextFlow(result, searchRequest));
            entryLabel.setPrefHeight(10);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            menuItems.add(item);
            item.setOnAction(actionEvent ->
            {
                setText(result);
                positionCaret(result.length());
                entriesPopup.hide();
            });
        }
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);
    }

    public SortedSet<String> getEntries() { return entries; }
}