package de.tudarmstadt.informatik.tk.kraken.android.sdk.login;

import android.app.ProgressDialog;

import com.microsoft.live.LiveAuthClient;
import com.microsoft.live.LiveAuthException;
import com.microsoft.live.LiveAuthListener;
import com.microsoft.live.LiveConnectSession;
import com.microsoft.live.LiveStatus;

import java.util.LinkedList;
import java.util.List;

import de.tudarmstadt.informatik.tk.kraken.sdk.R;
import de.tudarmstadt.informatik.tk.kraken.android.sdk.ui.activities.accounts.AccountsAdapter;
import de.tudarmstadt.informatik.tk.kraken.android.sdk.common.SocialNetworkProvider;

public class LoginLive extends AbstractLogin implements ILoginData {

	private ProgressDialog m_progDialog = null;

	private static LoginLive m_instance;

	private LoginLive() {
	}

	public static LoginLive getInstance() {
		if (m_instance == null)
			m_instance = new LoginLive();
		return m_instance;
	}

	@SuppressWarnings("unused")
	private AccountsAdapter m_adapter;

	final public static String CLIENT_ID = "000000004410F9C6";
	final public static String CLIENT_SECRET = "Jb6AufX3iOPo9g4xsxILWicY7YD2uUbi";

	@Override
	public int getLogoId() {
		return R.drawable.ic_microsoft;
	}

	@Override
	public void login(final AccountsAdapter adapter) {
		// m_progDialog = ProgressDialog.show(m_ctxActivity, "Please wait",
		// "We are logging in...");
		m_adapter = adapter;

		LiveAuthClient authClient = new LiveAuthClient(m_ctxActivity, CLIENT_ID);

		List<String> liScopes = new LinkedList<String>();
		liScopes.add("wl.basic");
		liScopes.add("wl.offline_access");

		authClient.login(m_ctxActivity, liScopes, new LiveAuthListener() {

			@Override
			public void onAuthError(LiveAuthException exception, Object userState) {
				exception.printStackTrace();
			}

			@Override
			public void onAuthComplete(LiveStatus status, LiveConnectSession session, Object userState) {

				String strAccessToken = session.getAccessToken();
				String strRefreshToken = session.getRefreshToken();

				handleReceivedToken(strAccessToken, strRefreshToken, m_ctxActivity);

				m_ctxActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						adapter.notifyDataSetChanged();
						adapter.refreshItem(getName());
					}
				});
			}
		});

	}

	public void dismissProgressDialog() {
		m_progDialog.dismiss();
	}

	@Override
	public SocialNetworkProvider getSocialNetworkProvider() {
		return SocialNetworkProvider.LIVE;
	}

}
