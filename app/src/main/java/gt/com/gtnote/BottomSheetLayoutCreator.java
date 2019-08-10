package gt.com.gtnote;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import static android.support.constraint.Constraints.TAG;

class BottomSheetLayoutCreator {
    
    private Resources res;
    private LayoutInflater layoutInflater;
    private ViewGroup root;
    
    private FlexboxLayout currentFlexBox = null;
    
    BottomSheetLayoutCreator(Resources res, LayoutInflater layoutInflater, ViewGroup root) {
        this.res = res;
        this.layoutInflater = layoutInflater;
        this.root = root;
    }
    
    void beginCategory(String name) {
        
        View categoryView = layoutInflater.inflate(R.layout.bottom_sheet_category, root, false);
        
        TextView categoryTitleTextView = categoryView.findViewById(R.id.bottom_sheet_category_title);
        categoryTitleTextView.setText(name);
        
        currentFlexBox = categoryView.findViewById(R.id.bottom_sheet_category_flexbox);
        
        root.addView(categoryView);
    }
    
    void addButton(int drawableResourceId, @Nullable String text, @Nullable View.OnClickListener onClickListener) {
        
        if (currentFlexBox == null) {
            Log.w(TAG, "addImageButton with text \""+text+"\", but currentFlexBox is null");
            return;
        }
    
        View view = layoutInflater.inflate(R.layout.bottom_sheet_button, currentFlexBox, false);
        Button button = view.findViewById(R.id.bottom_sheet_image_button);
    
        button.setCompoundDrawablesWithIntrinsicBounds(res.getDrawable(drawableResourceId), null, null, null);
    
        if (text != null) {
            button.setText(text);
        }
    
        if (onClickListener != null) {
            button.setOnClickListener(onClickListener);
        }
        
        currentFlexBox.addView(view);
    }
}