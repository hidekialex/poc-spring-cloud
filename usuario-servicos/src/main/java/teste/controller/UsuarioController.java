package teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import teste.configuracao.seguranca.ResourceOwner;
import teste.controller.response.UsuarioResponse;
import teste.service.UsuarioService;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public UsuarioResponse info(OAuth2Authentication auth) {
        Integer id = getUserId(auth);
        UsuarioResponse response = usuarioService.getInfoById(id);
        return response;
    }

    protected Integer getUserId(OAuth2Authentication auth) {
        ResourceOwner owner = (ResourceOwner) auth.getUserAuthentication().getPrincipal();
        return owner.getId();
    }
}
