package com.example.evgeniy.yalantistask2.data;


import android.content.Context;

import com.example.evgeniy.yalantistask2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Evgeniy
 */
public class InitData {
    public static final int I1 = 10000000;

    private static DateFormat sFormatter;

    public static List<AppealEntity> getModel(Context ctx, State state) {

        String[] urls = ctx.getResources().getStringArray(R.array.urlArray);
        List<AppealEntity> result = new ArrayList<>(10);
        Random r = new Random((new Date()).getTime());

        for (int i = 1; i <= 10; i++) {

            int randomInt = r.nextInt(I1);
            String number = String.format("CE-%d08", randomInt);

            String category = "";
            String responsible = "";
            String fullText = "";
            int iconId = 0;

            switch (randomInt % 2) {
                case 0:
                    category = ctx.getString(R.string.category1);
                    responsible = ctx.getString(R.string.respon_1);
                    fullText = ctx.getString(R.string.full_text1);
                    iconId = R.drawable.ic_engin;
                    break;
                case 1:
                    category = ctx.getString(R.string.category2);
                    responsible = ctx.getString(R.string.respon_2);
                    fullText = ctx.getString(R.string.full_text2);
                    iconId = R.drawable.ic_build;
                    break;
            }

            Date dtCreated = new Date();
            dtCreated.setDate(r.nextInt(31) + 1);
            dtCreated.setMonth(1);

            Date dtReg = new Date();
            dtReg.setDate(r.nextInt(31) + 1);
            dtReg.setMonth(2);

            result.add(new AppealEntity(i, number, category, state, dtCreated, dtReg, new Date(),
                    responsible, iconId, randomInt % 3,
                    fullText,
                    Arrays.asList(urls)));
        }

        return result;
    }

    public static DateFormat getFormatter() {

        if (sFormatter == null) {
            sFormatter = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());
        }

        return sFormatter;
    }


}
