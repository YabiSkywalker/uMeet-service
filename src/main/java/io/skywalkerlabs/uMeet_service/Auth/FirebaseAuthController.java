package io.skywalkerlabs.uMeet_service.Auth;


import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/api/auth"})
public class FirebaseAuthController {

    private final FirebaseService firebaseService;

    public FirebaseAuthController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Operation(summary = "Authenticate into API")
    @PostMapping("/login")
    public ResponseEntity<?> firebaseAuthenticate(@RequestHeader("Authorization") String token) throws FirebaseAuthException {

         if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token format");
        }

        try {
            // Extract the token value
            String idToken = token.substring(7);

            // Verify the token
            FirebaseToken decodedToken = firebaseService.verifyToken(idToken);

            
            return ResponseEntity.ok(decodedToken);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + e.getMessage());
        }
    }

}
