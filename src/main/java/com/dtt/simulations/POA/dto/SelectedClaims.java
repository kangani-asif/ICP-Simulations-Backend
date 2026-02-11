package com.dtt.simulations.POA.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public  class SelectedClaims {
        @JsonProperty("Document")
        private List<String> Document;

        public List<String> getDocument() {
            return Document;
        }

        public void setDocument(List<String> Document) {
            this.Document = Document;
        }
    }