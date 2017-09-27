package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
@Produces("text/plain")
public class IbanValidatorForMore {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream) throws IOException {

		writeToFile(uploadedInputStream);

		String output = "File uploaded to : " + uploadedInputStream + ".out";

		return Response.status(200).entity(output).build();

	}

	private void writeToFile(InputStream uploadedInputStream) {

		IbanValidator validator = new IbanValidator();

		BufferedReader in = null;
		BufferedWriter out = null;

		String line = "";
		try {
			in = new BufferedReader(new FileReader(uploadedInputStream + ".txt"));

			out = new BufferedWriter(new FileWriter(uploadedInputStream + ".out"));

			while ((line = in.readLine()) != null) {
				if (validator.validator(line)) {
					System.out.println(line + ";true" + "\n");
					out.write(line + ";true" + "\n");
				} else {
					System.out.println(line + ";false" + "\n");
					out.write(line + ";false" + "\n");
				}
			}

			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}
}
