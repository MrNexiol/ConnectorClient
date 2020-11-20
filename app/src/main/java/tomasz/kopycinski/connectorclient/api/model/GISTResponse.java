package tomasz.kopycinski.connectorclient.api.model;

import java.util.List;

import tomasz.kopycinski.connectorclient.api.model.Contact;

public class GISTResponse {
    private List<Contact> contacts;
    private String next_page_token;
    private String next_sync_token;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public String getNext_sync_token() {
        return next_sync_token;
    }

    public void setNext_sync_token(String next_sync_token) {
        this.next_sync_token = next_sync_token;
    }

    @Override
    public String toString() {
        return "GISTResponse{" +
                "contacts=" + contacts +
                ", next_page_token='" + next_page_token + '\'' +
                ", next_sync_token='" + next_sync_token + '\'' +
                '}';
    }
}
