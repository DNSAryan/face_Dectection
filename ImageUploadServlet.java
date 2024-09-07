import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
public class ImageUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the uploaded file part
        Part filePart = request.getPart("imageFile");

        // Get the file name
        String fileName = filePart.getSubmittedFileName();

        // You can now process the uploaded image here.
        // For example, you can save the file to a specific location on the server.

        // For demonstration purposes, let's just print the file name to the console.
        System.out.println("Uploaded file name: " + fileName);
    }
}
