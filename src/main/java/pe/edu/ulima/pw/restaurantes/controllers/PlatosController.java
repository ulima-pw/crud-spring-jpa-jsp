package pe.edu.ulima.pw.restaurantes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.ulima.pw.restaurantes.entities.CategoriaEntity;
import pe.edu.ulima.pw.restaurantes.entities.PlatoEntity;
import pe.edu.ulima.pw.restaurantes.forms.EditarPlatoForm;
import pe.edu.ulima.pw.restaurantes.repositories.CategoriaRepository;
import pe.edu.ulima.pw.restaurantes.repositories.PlatoRepository;

@Controller
public class PlatosController {

    @Autowired
    private CategoriaRepository repoCat;
    @Autowired
    private PlatoRepository repoPlato;

    // http://localhost:8080/listar_platos?edit=yes
    @RequestMapping(value = "/listar_platos", method = RequestMethod.GET)
    public String cargarListadoPlatos(
        Model model, 
        @RequestParam(name = "modo", required = false) String modo,
        @RequestParam(name = "platoid", required = false) String platoId
    ) {
        List<PlatoEntity> listaPlatos = repoPlato.findAll();
        model.addAttribute("listaPlatos", listaPlatos);
        if (modo == null) {
            model.addAttribute("abroElModal", false);
        }else {
            if (modo.equals("edit") || modo.equals("new")) {
                model.addAttribute("abroElModal", true);
                if (modo.equals("edit")) {
                    // Obtener los datos del plato que queremos
                    if (platoId != null) {
                        Long id = Long.parseLong(platoId);
                        Optional<PlatoEntity> opPlato = repoPlato.findById(id);
                        if (opPlato.isPresent()) {
                            PlatoEntity plato = opPlato.get();
                            model.addAttribute("plato", plato);
                        }
                    }
                }

                // Obtener la lista de categorias
                List<CategoriaEntity> categorias = repoCat.findAll();
                model.addAttribute("listaCategorias", categorias);
                model.addAttribute("modo", modo);
            }else {
                model.addAttribute("abroElModal", false);
            }
        }
        
        return "listar-platos";
    }

    @RequestMapping(value="/guardar_plato", method = RequestMethod.POST)
    public String guardarPlato(EditarPlatoForm formPlato) {
        PlatoEntity plato = new PlatoEntity();
        plato.setNombre(formPlato.getPlato_nombre());
        plato.setEstado(Integer.parseInt(formPlato.getPlato_estado()));

        Long categoriaId = Long.parseLong(formPlato.getPlato_categoria());
        Optional<CategoriaEntity> opCategoria = repoCat.findById(categoriaId);
        if (opCategoria.isPresent()) {
            plato.setCategoria(opCategoria.get());
            repoPlato.saveAndFlush(plato);
            System.out.println("Se guardo un nuevo plato");
        }

        
        return "redirect:/listar_platos";
    }

    @RequestMapping(value="/modificar_plato", method = RequestMethod.POST)
    public String modificarPlato(EditarPlatoForm formPlato) {
        Long platoId = Long.parseLong(formPlato.getPlato_id());
        Optional<PlatoEntity> opPlato = repoPlato.findById(platoId);
        if (opPlato.isPresent()) {
            PlatoEntity plato = opPlato.get();
            Long categoriaId = Long.parseLong(formPlato.getPlato_categoria());
            Optional<CategoriaEntity> opCategoria = repoCat.findById(categoriaId);
            if (opCategoria.isPresent()) {
                plato.setNombre(formPlato.getPlato_nombre());
                plato.setCategoria(opCategoria.get());
                plato.setEstado(Integer.parseInt(formPlato.getPlato_estado()));
                repoPlato.saveAndFlush(plato);
            }else{
                System.err.println("No se encuentra categoria para modificacion de plato");
            }
        }else {
            System.err.println("No se encuentra plato para modificacion");
        }

        
        return "redirect:/listar_platos";
    }

    @RequestMapping(value = "/eliminar_plato", method = RequestMethod.GET)
    public String eliminarPlato(
        @RequestParam(name = "platoid") String id
    ) {
        Long platoId = Long.parseLong(id);
        Optional<PlatoEntity> opPlato = repoPlato.findById(platoId);
        if (opPlato.isPresent()) {
            repoPlato.delete(opPlato.get());
        }else {
            System.err.println("No se pudo eliminar porque no se encontro plato");
        }
        return "redirect:/listar_platos";
    }

}