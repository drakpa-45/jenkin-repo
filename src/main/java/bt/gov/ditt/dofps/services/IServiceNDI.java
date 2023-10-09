package bt.gov.ditt.dofps.services;

import bt.gov.ditt.dofps.dto.NdiDTO;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pema Drakpa on 1/24/2020.
 */
public interface IServiceNDI {
    public NdiDTO getPasswordLessLogin(HttpServletRequest request, HttpServletResponse responses) throws IOException, InterruptedException;

    public SseEmitter subscribe(String threadId) throws IOException;
}
