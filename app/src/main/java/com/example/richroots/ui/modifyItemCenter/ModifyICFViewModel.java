package com.example.richroots.ui.modifyItemCenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModifyICFViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ModifyICFViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}