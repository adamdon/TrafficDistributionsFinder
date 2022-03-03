package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.databinding.SearchFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.SearchViewModel;


public class SearchFragment extends AbstractFragment
{
    public SearchFragment(App app)
    {
        super(app);
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
        searchFragmentBinding.searchTextButton.setOnClickListener(view -> searchViewModel.onSearchTextButtonClick());
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
