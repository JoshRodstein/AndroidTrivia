package com.example.josh.assignment1;

/**
 * Created by josh on 1/24/18.
 */

class GlossaryEntry {
    private String term;
    private String definition;

    public GlossaryEntry(String t, String d){
        this.term = t;
        this.definition = d;
    }

    public String getTerm(){
        return this.term;
    }

    public String getDefinition(){
        return this.definition;
    }

    public void setTerm(String t){
        this.term = t;
    }

    public void setDefinition(String d){
        this.definition = d;
    }

}
