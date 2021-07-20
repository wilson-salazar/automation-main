/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamarind.controllers;

import com.tamarind.ejb.ClienteFacade;
import com.tamarind.ejb.DestinoFacade;
import com.tamarind.ejb.PaisFacade;
import com.tamarind.entites.Cliente;
import com.tamarind.entites.ClienteDestinoTarifa;
import com.tamarind.entites.Destino;
import com.tamarind.utils.Utilities;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Developer
 */
@Named(value = "clientController")
@ViewAccessScoped
public class ClientController implements Serializable {

    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private PaisFacade paisFacade;

    @EJB
    private DestinoFacade destinoFacade;

    @Inject
    private SecurityController securityController;
    @Inject
    private SessionController sessionController;

    //TABLA CLIENTES
    private String filtro = "";
    private LazyDataModel<Object[]> lista;
    private List<Object[]> rawData;

    private boolean refreshCounter;
    private boolean refreshData;

    private int total;
    private int pageSize;
    private int firstIdx;
    private Cliente clienteCrear;
    private Cliente clienteEditar;
    private Cliente clienteDestinoTarifa;

    private Boolean estado = true;

    private List<String> FILTROS = Arrays.asList("upper(nombre)");

    List<Cliente> clientes;

    private List<Destino> destinos;
    private List<Destino> listDestinos;
    private List<Destino> selectedDestinos;
    private List<Destino> filteredDestinos;
    private List<ClienteDestinoTarifa> clienteDestinoTarifaList;
    private String filtroDestinos;
    
    private UploadedFile file;

    public ClientController() {
    }

    @PostConstruct
    public void init() {
        pageSize = 10;
        destinos = destinoFacade.findAll();
        filtrar();
    }

    public void upload() {
        if (file != null) {
            System.out.println("Nombre del file: >>>>>>>>>>>>>>>>>" + file.getFileName());
        }else{
            System.out.println("No llego :( ");
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        
        if (event.getFile() != null) {
            System.out.println("Nombre del file: >>>>>>>>>>>>>>>>>" + event.getFile().getFileName());
        }else{
            System.out.println("No llego :( ");
        }
    }
    
    
    public String iniciarClienteDestinoTarifaList(Number id) {
        filtroDestinos = "";
        clienteDestinoTarifaList = new ArrayList<>();
        selectedDestinos = new ArrayList<>();
        clienteDestinoTarifa = clienteFacade.find(id);
        listDestinos = new ArrayList<>(destinos);
        if (!clienteDestinoTarifa.getClienteDestinoTarifaList().isEmpty()) {
            for (ClienteDestinoTarifa destinoTar : clienteDestinoTarifa.getClienteDestinoTarifaList()) {
                listDestinos.remove(destinoTar.getDestino());
            }
        }
        filteredDestinos = new ArrayList<>(listDestinos);
        return Utilities.redirect("/secure/client/destino_tarifa.xhtml");
    }

    public void filtrarDestinos() {
        if (!filtroDestinos.isEmpty()) {
            filteredDestinos = listDestinos.stream().filter(x -> x.getDescripcion().toUpperCase().contains(filtroDestinos.toUpperCase()) || x.getCodigo().toUpperCase().contains(filtroDestinos.toUpperCase())).collect(Collectors.toList());
        } else {
            filteredDestinos = new ArrayList<>(listDestinos);
        }
    }

    public void removeDestination(int index) {
        listDestinos.add(clienteDestinoTarifa.getClienteDestinoTarifaList().get(index).getDestino());
        filteredDestinos.add(clienteDestinoTarifa.getClienteDestinoTarifaList().get(index).getDestino());
        clienteDestinoTarifa.getClienteDestinoTarifaList().remove(index);
    }

    public void filtrar() {
        refreshCounter = true;
        refreshData = true;
        firstIdx = 0;
        if (filtro == null) {
            filtro = "";
        }
        setLista(new LazyModel(filtro.toUpperCase()));
    }

    public String iniciarCrear() {
        clienteCrear = new Cliente();
        clienteCrear.setActivo(true);
        return Utilities.redirect("/secure/client/crear.xhtml");
    }

    public String iniciarEditar(Number id) {
        try {
            clienteEditar = clienteFacade.find(id);
            return Utilities.redirect("/secure/client/editar.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Llenado de la segunda tabla destinoTarifa  
    public void llenarListaClienteDestino() {

        if (clienteDestinoTarifa.getClienteDestinoTarifaList().isEmpty()) {
            for (Destino des : selectedDestinos) {
                des.setClienteDestinoTarifaList(clienteDestinoTarifaList);
                clienteDestinoTarifaList.add(new ClienteDestinoTarifa(
                        true,
                        "[1/1]",
                        clienteDestinoTarifa,
                        des,
                        BigDecimal.ZERO,
                        new Date(),
                        sessionController.getUsuario()));
                listDestinos.remove(des);
                filteredDestinos.remove(des);
            }
            selectedDestinos = new ArrayList<>();
            clienteDestinoTarifa.setClienteDestinoTarifaList(clienteDestinoTarifaList);
        } else {
            clienteDestinoTarifaList = new ArrayList();
            clienteDestinoTarifaList.addAll(clienteDestinoTarifa.getClienteDestinoTarifaList());

            for (Destino des : selectedDestinos) {
                des.setClienteDestinoTarifaList(clienteDestinoTarifaList);
                clienteDestinoTarifaList.add(new ClienteDestinoTarifa(
                        true,
                        "[1/1]",
                        clienteDestinoTarifa,
                        des,
                        BigDecimal.ZERO,
                        new Date(),
                        sessionController.getUsuario()));
                listDestinos.remove(des);
                filteredDestinos.remove(des);
            }
            selectedDestinos = new ArrayList<>();
            clienteDestinoTarifa.setClienteDestinoTarifaList(clienteDestinoTarifaList);
        }

    }

    public void actualizarClienteDestinoTarifa() {
        try {
            clienteFacade.edit(clienteDestinoTarifa);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info", "Client updated successfully."));

        } catch (Exception e) {
            //mensaje
            e.printStackTrace();
        }
    }

    public String crear(boolean exit) {
        try {
            clienteCrear.setFechaCreacion(new Date());
            clienteCrear.setUsuarioCreacion(sessionController.getUsuario());
            clienteFacade.create(clienteCrear);
            clienteCrear = new Cliente();
            refreshData = true;
            //mensaje
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info", "Client created successfully."));
            if (exit) {
                return Utilities.redirect("/secure/client/listar.xhtml");
            }
        } catch (Exception e) {
            //mensaje
            e.printStackTrace();
        }
        return null;
    }

    public String editar() {
        try {
            clienteFacade.edit(clienteEditar);
            clienteEditar = new Cliente();
            //mensaje
            refreshData = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Info", "Client updated successfully."));
            return Utilities.redirect("/secure/client/listar.xhtml");
        } catch (Exception e) {
            //mensaje
            e.printStackTrace();
        }
        return null;
    }

    public String desactivar(Number id) {
        try {
            Cliente mCliente = clienteFacade.find(id);
            if (mCliente != null) {
                mCliente.setActivo(false);
                clienteFacade.edit(mCliente);
                //mensaje
                refreshData = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "client has been disabled successfully."));
            } else {
                refreshData = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Warn", "Client doesn't exist."));
            }

        } catch (Exception e) {
            //mensaje
            e.printStackTrace();
        }
        return null;
    }

    public String activar(Number id) {
        try {
            Cliente mCliente = clienteFacade.find(id);
            if (mCliente != null) {
                mCliente.setActivo(true);
                clienteFacade.edit(mCliente);
                //mensaje
                refreshData = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "Client has been enabled successfully."));
            } else {
                refreshData = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Warn", "Client doesn't exist."));
            }

        } catch (Exception e) {
            //mensaje
            e.printStackTrace();
        }
        return null;
    }

    public final class LazyModel extends LazyDataModel<Object[]> {

        protected String filtro;

        public LazyModel(String filtro) {
            this.filtro = filtro;
        }

        @Override
        public Object getRowKey(Object[] item) {
            return item[0];
        }

        @Override
        public List<Object[]> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
            try {

                setPageSize(pageSize);
                if (refreshData) {
                    rawData = (List<Object[]>) clienteFacade.filtrar(first, pageSize,
                            filtro);
                    refreshData = false;
                    firstIdx = first;
                }
                if (refreshCounter) {
                    total = (int) ((long) clienteFacade.filtrarcont(filtro
                    ));
                    refreshCounter = false;
                }
                setRowCount(total);
                return rawData;
            } catch (Exception ex) {
                //addFlashMessageFaltalJs("msgs", msg("error.mensaje_gen"));
            }
            return null;
        }
    }

    public Cliente getClienteCrear() {
        return clienteCrear;
    }

    public void setClienteCrear(Cliente clienteCrear) {
        this.clienteCrear = clienteCrear;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public LazyDataModel<Object[]> getLista() {
        return lista;
    }

    public void setLista(LazyDataModel<Object[]> lista) {
        this.lista = lista;
    }

    public List<Object[]> getRawData() {
        return rawData;
    }

    public void setRawData(List<Object[]> rawData) {
        this.rawData = rawData;
    }

    public boolean isRefreshCounter() {
        return refreshCounter;
    }

    public void setRefreshCounter(boolean refreshCounter) {
        this.refreshCounter = refreshCounter;
    }

    public boolean isRefreshData() {
        return refreshData;
    }

    public void setRefreshData(boolean refreshData) {
        this.refreshData = refreshData;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirstIdx() {
        return firstIdx;
    }

    public void setFirstIdx(int firstIdx) {
        this.firstIdx = firstIdx;
    }

    public void onPage(PageEvent e) {
        this.refreshData = true;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Cliente getClienteEditar() {
        return clienteEditar;
    }

    public void setClienteEditar(Cliente clienteEditar) {
        this.clienteEditar = clienteEditar;
    }

    public List<ClienteDestinoTarifa> getClienteDestinoTarifaList() {
        return clienteDestinoTarifaList;
    }

    public void setClienteDestinoTarifaList(List<ClienteDestinoTarifa> clienteDestinoTarifaList) {
        this.clienteDestinoTarifaList = clienteDestinoTarifaList;
    }

    public Cliente getClienteDestinoTarifa() {
        return clienteDestinoTarifa;
    }

    public void setClienteDestinoTarifa(Cliente clienteDestinoTarifa) {
        this.clienteDestinoTarifa = clienteDestinoTarifa;
    }

    public List<Destino> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<Destino> destinos) {
        this.destinos = destinos;
    }

    public List<Destino> getSelectedDestinos() {
        return selectedDestinos;
    }

    public void setSelectedDestinos(List<Destino> selectedDestinos) {
        this.selectedDestinos = selectedDestinos;
    }

    public List<Destino> getFilteredDestinos() {
        return filteredDestinos;
    }

    public void setFilteredDestinos(List<Destino> filteredDestinos) {
        this.filteredDestinos = filteredDestinos;
    }

    public List<Destino> getListDestinos() {
        return listDestinos;
    }

    public void setListDestinos(List<Destino> listDestinos) {
        this.listDestinos = listDestinos;
    }

    public String getFiltroDestinos() {
        return filtroDestinos;
    }

    public void setFiltroDestinos(String filtroDestinos) {
        this.filtroDestinos = filtroDestinos;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
