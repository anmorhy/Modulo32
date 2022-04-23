package spring.boot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.boot.model.Pessoa;
import spring.boot.repository.PessoaRepository;

@Controller 
public class PessoaController {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @RequestMapping(method = RequestMethod.GET, value="/cadastropessoa" )
    public ModelAndView inicio() {
        
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;    
    } 
    
    @RequestMapping(method = RequestMethod.POST, value = "/salvarpessoa")
    public ModelAndView salvar(Pessoa pessoa){

        pessoaRepository.save(pessoa);
        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        andView.addObject("pessoas", pessoasIt);
        andView.addObject("pessoaobj", new Pessoa());

        return andView;
    }

    @RequestMapping(method = RequestMethod.GET, value="/listapessoas" )
    public ModelAndView pessoas(){

        ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        andView.addObject("pessoas", pessoasIt);
        andView.addObject("pessoaobj", new Pessoa());
        return andView;
    }

    @RequestMapping(method = RequestMethod.GET, value="/editarpessoa/{idpessoa}" )
    //@GetMapping("/editarpessoa/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") long idpessoa){

        Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoaobj", pessoa.get());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value="/removerpessoa/{idpessoa}" )
    public ModelAndView remover(@PathVariable("idpessoa") long idpessoa){

        pessoaRepository.deleteById(idpessoa);

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    //@PostMapping("/pesquisarpessoa")
    @RequestMapping(method = RequestMethod.POST, value = "/pesquisarpessoa")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa){
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findPessoaByName(nomepesquisa));
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value="/telefones/{idpessoa}" )
    //@GetMapping("/editarpessoa/{idpessoa}")
    public ModelAndView telefones(@PathVariable("idpessoa") long idpessoa){

        Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoaobj", pessoa.get());
        return modelAndView;
    }

}
