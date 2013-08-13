/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author juan2ramos
 */
public class FacadePC {

    private Bios bios;
    private DiscoDuro discoDuro;
    private SistemaOperativo sistemaOperativo;

    public FacadePC() {
        bios = new Bios();
        discoDuro = new DiscoDuro();
        sistemaOperativo = new SistemaOperativo();
    }
    public void EncenderPc(){
        bios.comprobando();
        discoDuro.LeerDD();
        sistemaOperativo.Cargando();
    }
    
}
