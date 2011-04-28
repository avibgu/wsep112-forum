package presentation.gui.workers;

import javax.swing.SwingWorker;

import presentation.gui.AddThreadPanel;
import presentation.gui.StartWindow;
import presentation.gui.ThreadsViewPanel;

import domain.ClientController;

public class AddThreadWorker extends SwingWorker<Void, Void>{

	private AddThreadPanel _panel;
	private StartWindow _startWindow;
	private ClientController _clientController;
	private String _forumId;
	private String _title;
	private String _body;
	
	public AddThreadWorker(AddThreadPanel panel,
			ClientController clientController, StartWindow startWindow, String forumId,
			String title,String body) {

		super();
		_panel = panel;
		_clientController = clientController;
		_forumId = forumId;
		_title = title;
		_body = body;
		_startWindow = startWindow;
	}
	
	protected Void doInBackground() throws Exception {
		
		_clientController.addThread(_forumId, _title, _body);
        
        return null;
	}

	protected void done(){
        _startWindow.getForum().displayForum(null); //setVisible false
        _startWindow.getForum().displayForum(
        		new ThreadsViewPanel( _clientController,_forumId,  _startWindow));
        _panel.setVisible(false);
		_panel.updateUI();
	}
}