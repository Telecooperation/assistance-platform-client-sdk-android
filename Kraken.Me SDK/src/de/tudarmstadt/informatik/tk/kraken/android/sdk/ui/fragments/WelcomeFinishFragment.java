package de.tudarmstadt.informatik.tk.kraken.android.sdk.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.tudarmstadt.informatik.tk.kraken.sdk.R;

/**
 * @author Karsten Planz
 */
public class WelcomeFinishFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome_finish, container, false);
    }
}