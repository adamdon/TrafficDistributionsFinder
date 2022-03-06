package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.databinding.SearchFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.SearchViewModel;


public class SearchFragment extends AbstractFragment
{
    public SearchFragment()
    {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        SearchViewModel searchViewModel;
        SearchFragmentBinding searchFragmentBinding;

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        searchFragmentBinding = SearchFragmentBinding.inflate(layoutInflater,viewGroup,false);
        searchFragmentBinding.backButton.setOnClickListener(view -> requireActivity().onBackPressed());
        searchFragmentBinding.searchTextButton.setOnClickListener(view ->
        {
            InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            searchViewModel.onSearchTextButtonClick();
        });
        searchFragmentBinding.searchTextInputEditText.setOnEditorActionListener((v, actionId, event) ->
        {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE))
            {
                InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                searchViewModel.onSearchTextButtonClick();
            }
            return false;
        });
        searchFragmentBinding.searchTextInputEditText.addTextChangedListener(new TextWatcher()
        {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable e) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                searchViewModel.setTextStringLiveData(s.toString());
            }
        });




        searchViewModel.getTextStringLiveData().observe(getViewLifecycleOwner(), textString -> searchFragmentBinding.searchTextButton.setText("Search: " + textString));


        return nestContentInTemplateFrameLayout(searchFragmentBinding.getRoot());
    }


}
