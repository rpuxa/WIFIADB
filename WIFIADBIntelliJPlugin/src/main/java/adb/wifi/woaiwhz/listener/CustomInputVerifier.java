package adb.wifi.woaiwhz.listener;

import adb.wifi.woaiwhz.base.Notify;
import adb.wifi.woaiwhz.base.Utils;

import javax.swing.*;

/**
 * Created by huazhou.whz on 2016/10/7.
 */
public class CustomInputVerifier extends InputVerifier {
    private final int mMaxNumber;
    private final int mDefaultNumber;

    /**
     * 0 <= input < maxNumber
     */
    public CustomInputVerifier(int maxNumber){
        mMaxNumber = maxNumber;
        mDefaultNumber = Integer.MIN_VALUE;
    }

    public CustomInputVerifier(int maxNumber,int defaultNumber) {
        mMaxNumber = maxNumber;
        mDefaultNumber = defaultNumber;
    }

    @Override
    public boolean verify(JComponent input) {
        if(!(input instanceof JTextField)){
            Notify.error();
            return false;
        }

        final JTextField current = (JTextField) input;
        final String text = current.getText();

        if(Utils.isBlank(text)){
            if(mDefaultNumber != Integer.MIN_VALUE){
                current.setText(String.valueOf(mDefaultNumber));
            }
            return true;
        }

        try {
            final int number = Integer.valueOf(text);
            if(number >= 0 && number < mMaxNumber){
                return true;
            }else {
                Notify.error(Utils.concat("Number should >= 0 and &lt ",String.valueOf(mMaxNumber),
                        " ,\nbut now is ",String.valueOf(number)));
                return false;
            }
        }catch (NumberFormatException e){
            Notify.error("Error input : " + text);
            return false;
        }
    }
}
