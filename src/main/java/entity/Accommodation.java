/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "accommodation")
@NamedQueries({
    @NamedQuery(name = "Accommodation.findAll", query = "SELECT a FROM Accommodation a"),
    @NamedQuery(name = "Accommodation.findByAid", query = "SELECT a FROM Accommodation a WHERE a.aid = :aid"),
    @NamedQuery(name = "Accommodation.findByName", query = "SELECT a FROM Accommodation a WHERE a.name = :name"),
    @NamedQuery(name = "Accommodation.findByCountry", query = "SELECT a FROM Accommodation a WHERE a.country = :country"),
    @NamedQuery(name = "Accommodation.findByState", query = "SELECT a FROM Accommodation a WHERE a.state = :state"),
    @NamedQuery(name = "Accommodation.findByCity", query = "SELECT a FROM Accommodation a WHERE a.city = :city"),
    @NamedQuery(name = "Accommodation.findByAddress", query = "SELECT a FROM Accommodation a WHERE a.address = :address"),
    @NamedQuery(name = "Accommodation.findByDescription", query = "SELECT a FROM Accommodation a WHERE a.description = :description"),
    @NamedQuery(name = "Accommodation.findByRoomNumber", query = "SELECT a FROM Accommodation a WHERE a.roomNumber = :roomNumber"),
    @NamedQuery(name = "Accommodation.findByType", query = "SELECT a FROM Accommodation a WHERE a.type = :type"),
    @NamedQuery(name = "Accommodation.findByCapacity", query = "SELECT a FROM Accommodation a WHERE a.capacity = :capacity"),
    @NamedQuery(name = "Accommodation.findByPrice", query = "SELECT a FROM Accommodation a WHERE a.price = :price")})
public class Accommodation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "aid")
    private Integer aid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "roomNumber")
    private String roomNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacity")
    private int capacity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aid")
    private Collection<Booking> bookingCollection;

    public Accommodation() {
    }

    public Accommodation(Integer aid) {
        this.aid = aid;
    }

    public Accommodation(Integer aid, String name, String country, String state, String city, String address, String description, String roomNumber, String type, int capacity, int price) {
        this.aid = aid;
        this.name = name;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.description = description;
        this.roomNumber = roomNumber;
        this.type = type;
        this.capacity = capacity;
        this.price = price;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonbTransient
    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accommodation)) {
            return false;
        }
        Accommodation other = (Accommodation) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Accommodation[ aid=" + aid + " ]";
    }
    
}
