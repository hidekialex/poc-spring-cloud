package teste.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import teste.controller.response.UsuarioResponse;
import teste.modelo.Usuario;
import teste.repository.UsuarioRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getInfoByIdSeUsuarioNaoExistir() {

        Integer id = 1;
        when(repository.getById(eq(id))).thenReturn(Optional.empty());
        try {
            service.getInfoById(id);
            assertTrue("Test error.", false);
        } catch(RuntimeException e) {
            assertEquals("Recurso não encontrado", e.getMessage());
        }
    }

    @Test
    public void getInfoByIdSeUsuarioExistir() {

        Integer id = 1;
        Usuario usuario = new Usuario("Teste", null);

        when(repository.getById(eq(id))).thenReturn(Optional.of(usuario));

        UsuarioResponse response = service.getInfoById(id);

        assertEquals("Teste", response.getNome());
    }
}
