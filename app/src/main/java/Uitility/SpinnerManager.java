package Uitility;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Surya on 11/07/2018.
 *  * Provides a global spinner API for a single activity
 * Use this when there are multiple network calls that are being performed in parallel
 * and we don't want to dismiss the spinner till all the calls are complete.
 *
 * The class keeps track of how many show() and hide() requests happen for a particular context
 * and only dismisses the spinner once the difference between count(show()) and count(hide())
 * reaches zero
 */

public class SpinnerManager {
    private static Map<Context, Integer> map = Collections.synchronizedMap(new HashMap<Context, Integer>());
    private static Map<Context, ProgressDialog> spinnerMap = Collections.synchronizedMap(new HashMap<Context, ProgressDialog>());

    public static void showSpinner(Context ctx, String text) {
       try{
           if (!map.containsKey(ctx)) {
               map.put(ctx, 1);
               spinnerMap.put(ctx, new ProgressDialog(ctx));
           }
           else {
               int value = map.get(ctx);
               map.put(ctx, value + 1);
           }

           spinnerMap.get(ctx).setMessage(text);
           spinnerMap.get(ctx).setCancelable(false);
           spinnerMap.get(ctx).show();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public static void hideSpinner(Context ctx) {
      try{
          if (!map.containsKey(ctx)) { return; }

          Integer spinnerCount = map.get(ctx);
          if (spinnerCount == 1) {
              map.remove(ctx);
              spinnerMap.get(ctx).dismiss();
              spinnerMap.remove(ctx);
          }
          else if (spinnerCount > 1){
              map.put(ctx, spinnerCount - 1);
          }
      }catch (Exception e){
          e.printStackTrace();
      }
      }
}
