package com.syntaxerror.smartbudget.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "budgetInfo")
public class budgetInfo extends Model {

    @Column(name = "budget_id")
    private  int budget_id ;

    @Column(name = "budget_name")
    private  String budget_name;

    public budgetInfo() {
    }

    public budgetInfo(int budget_id, String budget_name) {
        this.budget_id = budget_id;
        this.budget_name = budget_name;
    }

    public int getBudget_id() {
        return budget_id;
    }

    public void setBudget_id(int budget_id) {
        this.budget_id = budget_id;
    }

    public String getBudget_name() {
        return budget_name;
    }

    public void setBudget_name(String budget_name) {
        this.budget_name = budget_name;
    }




}
