/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AgendamentoDAO;
import dao.LoteVacinasDAO;
import dao.PacientesDAO;
import dao.VacinasDAO;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agendamento;
import model.Paciente;
import model.Vacinas;

/**
 *
 * @author vitor
 */
public class AgendamentoController implements IController {

    private final AgendamentoDAO dao;
    private final PacientesDAO pacientesDAO;
    private final VacinasDAO vacinasDAO;
    private final Agendamento agendamento;
    private final LoteVacinasDAO loteVacinasDAO;

    public AgendamentoController() throws SQLException, IOException {
        this.dao = new AgendamentoDAO();
        this.pacientesDAO = new PacientesDAO();
        this.vacinasDAO = new VacinasDAO();
        this.loteVacinasDAO = new LoteVacinasDAO();
        this.agendamento = new Agendamento();
    }

    @Override
    public void inserir(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        agendamento.setDataDose(java.sql.Date.valueOf(request.getParameter("dataDose")));
        agendamento.setQuantidadeVac(Integer.valueOf(request.getParameter("quantidadeVac")));
       
        //OBJECT PACIENTE SET
        this.pacientesDAO.paciente.setId(Integer.valueOf(request.getParameter("idPaciente")));
        pacientesDAO.buscar(this.pacientesDAO.paciente.getId());
        agendamento.setPaciente(this.pacientesDAO.paciente);
        
        //OBJECT VACINAS SET
        this.loteVacinasDAO.loteVacinas.setVacina(new Vacinas(Integer.valueOf(request.getParameter("idVacinas"))));
        vacinasDAO.buscar(this.loteVacinasDAO.loteVacinas.getVacina().getId());
        loteVacinasDAO.loteVacinas.setVacina(vacinasDAO.vacina);
        agendamento.setVacinas(loteVacinasDAO.loteVacinas.getVacina());
        
        dao.cadastrar(agendamento);
        loteVacinasDAO.descVacina(agendamento.getQuantidadeVac(), agendamento.getVacinas().getId());
        response.sendRedirect("listar");
    }

    @Override
    public void listar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ClassNotFoundException {
        List<Agendamento> listAgendamento = dao.listar();
        request.setAttribute("listarAgendamentos", listAgendamento);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarAgendamento.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("agendamentoForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void editForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantidade = Integer.valueOf(request.getParameter("quantidadeVac"));
        int idVacinas = Integer.parseInt(request.getParameter("idVacinas"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("agendamentoForm.jsp");
        request.setAttribute("agendamento", dao.buscar(id));
        loteVacinasDAO.cresVacina(quantidade, idVacinas);
        dispatcher.forward(request, response);

    }

    @Override
    public void deletar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantidade = Integer.valueOf(request.getParameter("quantidadeVac"));
        int idVacinas = Integer.parseInt(request.getParameter("idVacinas"));
        dao.excluir(id);
        loteVacinasDAO.cresVacina(quantidade, idVacinas);
        response.sendRedirect("listar");

    }

    @Override
    public void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        agendamento.setDataDose(java.sql.Date.valueOf(request.getParameter("dataDose")));
        agendamento.setQuantidadeVac(Integer.valueOf(request.getParameter("quantidadeVac")));

        //OBJECT PACIENTE SET
        this.pacientesDAO.paciente.setId(Integer.valueOf(request.getParameter("idPaciente")));
        pacientesDAO.buscar(this.pacientesDAO.paciente.getId());
        agendamento.setPaciente(this.pacientesDAO.paciente);
        
        //OBJECT VACINAS SET
        this.loteVacinasDAO.loteVacinas.setVacina(new Vacinas(Integer.valueOf(request.getParameter("idVacinas"))));
        vacinasDAO.buscar(this.loteVacinasDAO.loteVacinas.getVacina().getId());
        loteVacinasDAO.loteVacinas.setVacina(vacinasDAO.vacina);
        agendamento.setVacinas(loteVacinasDAO.loteVacinas.getVacina());

        dao.atualizar(agendamento);
        loteVacinasDAO.descVacina(agendamento.getQuantidadeVac(), agendamento.getVacinas().getId());
        response.sendRedirect("listar");
    }
}
