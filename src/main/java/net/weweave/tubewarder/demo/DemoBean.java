package net.weweave.tubewarder.demo;

import net.weweave.tubewarder.client.*;

import javax.faces.bean.ManagedBean;
import java.net.ConnectException;

@ManagedBean(name="demoBean", eager=true)
public class DemoBean {
    private String email = "your@email.address";
    private String firstname = "Your Name";

    public void sendMessage() {
        TubewarderClient client = new TubewarderRestClient("http://localhost:8080");
        SendRequest req = new SendRequest("b7f61499-7125-4b95-9503-00b75a0edf56");
        req.setChannel("Email");
        req.setTemplate("Welcome_New_User");
        req.setRecipient(new Address(getEmail(), getFirstname()));
        req.addModelParam("firstname", getFirstname());
        req.setEcho(true);
        try {
            SendResponse resp = client.send(req);
            handleResponse(resp);
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(SendResponse resp) {
        System.out.println("--> Error Code       = " + resp.getError());
        System.out.println("--> Rendered Subject = " + resp.getSubject());
        System.out.println("--> Rendered Content = " + resp.getContent());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
