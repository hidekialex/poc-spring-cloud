package teste.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import teste.controller.response.UsuarioResponse;
import teste.service.UsuarioService;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsuarioControllerTest {

    @Spy
    @InjectMocks
    private UsuarioController controller;

    @Mock
    private UsuarioService service;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void info() throws Exception {

        Integer id = 1;
        UsuarioResponse response = new UsuarioResponse();
        response.setNome("Teste");

        doReturn(id).when(controller).getUserId(any(OAuth2Authentication.class));
        when(service.getInfoById(eq(id))).thenReturn(response);

        mockMvc.perform(get("/info")).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Teste")).isString());

    }
}
