package wzhkun.securepw.ui.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wzhkun.securepw.R;

/**
 * Created by wangzehao on 2015/8/8.
 */
public class Imexport {

    private TextView imexport_label_imexport_from;
    private Button imexport_choose;
    private EditText imexport_from;
    private TextView imexport_label_imexport_password;
    private EditText imexport_password;
    private CheckBox imexport_use_my_password;
    private Button imexport_imexport;
    private Button imexport_cancel;

    private RelativeLayout imexportView;
    private MainActivity context;

    private Imexport(){

    }

    public static View getImport(MainActivity context){
        Imexport im=new Imexport();
        im.initCommonPart(context);
        im.setImportText();
        im.setImportListener();
        return im.imexportView;
    }

    public static View getExport(MainActivity context){
        Imexport im=new Imexport();
        im.initCommonPart(context);
        im.setExportText();
        im.setExportListener();
        return im.imexportView;
    }

    private void initCommonPart(MainActivity context){
        LayoutInflater iInflater = LayoutInflater.from(context);
        imexportView= (RelativeLayout) iInflater.inflate(R.layout.imexport, null);

        this.context=context;

        imexport_label_imexport_from= (TextView) imexportView.getChildAt(0);
        imexport_choose= (Button) imexportView.getChildAt(1);
        imexport_from= (EditText) imexportView.getChildAt(2);
        imexport_label_imexport_password= (TextView) imexportView.getChildAt(3);
        imexport_password= (EditText) imexportView.getChildAt(4);
        imexport_use_my_password= (CheckBox) imexportView.getChildAt(5);
        imexport_imexport= (Button)imexportView.getChildAt(6);
        imexport_cancel= (Button) imexportView.getChildAt(7);

        setCommonListener();
    }

    private void setImportText(){
        imexport_label_imexport_from.setText(R.string.import_file);
        imexport_label_imexport_password.setText(R.string.import_file_password);
        imexport_imexport.setText(R.string.import_);
    }

    private void setExportText(){
        imexport_label_imexport_from.setText(R.string.export_file);
        imexport_label_imexport_password.setText(R.string.export_file_password);
        imexport_imexport.setText(R.string.export);
    }

    private void setImportListener(){

    }

    private void setExportListener(){

    }

    private void setCommonListener(){
        imexport_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private void cancel(){
        context.showSafeBox(null);
    }
}
