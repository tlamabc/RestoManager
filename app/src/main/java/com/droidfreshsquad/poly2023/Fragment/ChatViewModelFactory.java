package com.droidfreshsquad.poly2023.Fragment;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.droidfreshsquad.poly2023.Fragment.ChatViewModel;


public class ChatViewModelFactory implements ViewModelProvider.Factory {
    public MyType myType;

    public ChatViewModelFactory(MyType myType) {
        this.myType = myType;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ChatViewModel.class)) {
            return (T) new ChatViewModel(myType.toString());
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
