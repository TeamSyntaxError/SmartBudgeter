package com.syntaxerror.smartbudget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.syntaxerror.smartbudget.tables.BudgetTable;
import com.syntaxerror.smartbudget.tables.User;

public class BudgetListAdapter extends CursorAdapter {
    public BudgetListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.budget_list_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView budgetName = (TextView) view.findViewById(R.id.budgetNameItem);
        String name = cursor.getString(cursor.getColumnIndex(BudgetTable.BUDGET_NAME));
        budgetName.setText(name);
    }
}
