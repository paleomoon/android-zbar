/*
 * Basic no frills app which integrates the ZBar barcode scanner with
 * the camera.
 * 
 * Created by lisah0 on 2012-02-24
 */

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
/* Import ZBar Class files */
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.util.Log;

public class Zbar {
    private ImageScanner scanner = null;

    static {
        System.loadLibrary("iconv");
    } 

    public void init() {
    	scanner = new ImageScanner();
    	//scanner.setConfig(0, Config.X_DENSITY, 3);
    	//scanner.setConfig(0, Config.Y_DENSITY, 3);
    	scanner.setConfig(0, Config.ENABLE, 0);
    	scanner.setConfig(Symbol.QRCODE, Config.ENABLE, 1);
    	
	}
  
    public String scanQRcode(byte[] data,int width,int height){
    	String codeInfo = "";
    	Image barcode = new Image(width, height, "BGR3");
        barcode.setData(data);
       
        try {
	        int result = scanner.scanImage(barcode.convert("Y800"));
	        if (result != 0) {
	        	SymbolSet syms = scanner.getResults();
	        	for (Symbol sym : syms) {
	        		codeInfo=sym.getData();
	            }
	        }
        }
        catch (Exception e){
        	Log.d("扫码错误", e.toString());
        }
        return codeInfo;
    }

}
