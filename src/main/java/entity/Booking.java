/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "booking")
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findByBid", query = "SELECT b FROM Booking b WHERE b.bid = :bid"),
    @NamedQuery(name = "Booking.findByBookingdate", query = "SELECT b FROM Booking b WHERE b.bookingdate = :bookingdate"),
    @NamedQuery(name = "Booking.findByTraveldate", query = "SELECT b FROM Booking b WHERE b.traveldate = :traveldate"),
    @NamedQuery(name = "Booking.findByNumbersoftravelers", query = "SELECT b FROM Booking b WHERE b.numbersoftravelers = :numbersoftravelers"),
    @NamedQuery(name = "Booking.findByTotalamount", query = "SELECT b FROM Booking b WHERE b.totalamount = :totalamount"),
    @NamedQuery(name = "Booking.findByStatus", query = "SELECT b FROM Booking b WHERE b.status = :status")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bid")
    private Integer bid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bookingdate")
    @Temporal(TemporalType.DATE)
    private Date bookingdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "traveldate")
    @Temporal(TemporalType.DATE)
    private Date traveldate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numbersoftravelers")
    private int numbersoftravelers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalamount")
    private BigInteger totalamount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bid")
    private Collection<Feedback> feedbackCollection;
    @JoinColumn(name = "aid", referencedColumnName = "aid")
    @ManyToOne(optional = false)
    private Accommodation aid;
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    @ManyToOne(optional = false)
    private Package pid;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Users username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bid")
    private Collection<Payment> paymentCollection;

    public Booking() {
    }

    public Booking(Integer bid) {
        this.bid = bid;
    }

    public Booking(Integer bid, Date bookingdate, Date traveldate, int numbersoftravelers, BigInteger totalamount, String status) {
        this.bid = bid;
        this.bookingdate = bookingdate;
        this.traveldate = traveldate;
        this.numbersoftravelers = numbersoftravelers;
        this.totalamount = totalamount;
        this.status = status;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(Date bookingdate) {
        this.bookingdate = bookingdate;
    }

    public Date getTraveldate() {
        return traveldate;
    }

    public void setTraveldate(Date traveldate) {
        this.traveldate = traveldate;
    }

    public int getNumbersoftravelers() {
        return numbersoftravelers;
    }

    public void setNumbersoftravelers(int numbersoftravelers) {
        this.numbersoftravelers = numbersoftravelers;
    }

    public BigInteger getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigInteger totalamount) {
        this.totalamount = totalamount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonbTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    public Accommodation getAid() {
        return aid;
    }

    public void setAid(Accommodation aid) {
        this.aid = aid;
    }

    public Package getPid() {
        return pid;
    }

    public void setPid(Package pid) {
        this.pid = pid;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    @JsonbTransient
    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bid != null ? bid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bid == null && other.bid != null) || (this.bid != null && !this.bid.equals(other.bid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Booking[ bid=" + bid + " ]";
    }
    
}
