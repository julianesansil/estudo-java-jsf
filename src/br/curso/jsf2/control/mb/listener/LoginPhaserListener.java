package br.curso.jsf2.control.mb.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.curso.jsf2.control.mb.LoginMB;
import antlr.debug.Event;

public class LoginPhaserListener implements PhaseListener {

	@Override
	public PhaseId getPhaseId() {
		//Interceptar requisições da view
		return PhaseId.RESTORE_VIEW;
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//Método em que ocorrerá a validação do usuário logado
	public void afterPhase(PhaseEvent event) {
		//Variável com acesso à arvore de componentes
		FacesContext context = event.getFacesContext();
		
		//Verificação de acesso à página login da aplicação
		if (context.getViewRoot().getViewId().equals("/login.xhtml"))
			return;
		
		LoginMB loginMB = context.getApplication().evaluateExpressionGet(context, "#{loginMB}", LoginMB.class);
	
		if (!loginMB.isUsuarioLogado()) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "login");
			context.renderResponse();
		}
		
	}
	
}
