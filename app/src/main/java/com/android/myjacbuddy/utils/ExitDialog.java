package com.android.myjacbuddy.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.android.myjacbuddy.R;
import com.google.android.material.button.MaterialButton;

public class ExitDialog {
    static Dialog dialog;
    static MaterialButton yes_btn , no_btn;

    public static void exit_dialog(Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.exit_dialog_layout);
        yes_btn = dialog.findViewById(R.id.yes_btn);
        no_btn = dialog.findViewById(R.id.no_btn);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        yes_btn.setOnClickListener(v -> {
            dialog.dismiss();
            ((Activity)context).finishAffinity();
        });
        no_btn.setOnClickListener(v -> dialog.dismiss());
    }
}
