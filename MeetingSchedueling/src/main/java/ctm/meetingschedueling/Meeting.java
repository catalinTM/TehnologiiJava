/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctm.meetingschedueling;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author catal
 */
@Entity
@Table(name = "meeting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meeting.findAll", query = "SELECT m FROM Meeting m"),
    @NamedQuery(name = "Meeting.findById", query = "SELECT m FROM Meeting m WHERE m.id = :id"),
    @NamedQuery(name = "Meeting.findByTopic", query = "SELECT m FROM Meeting m WHERE m.topic = :topic"),
    @NamedQuery(name = "Meeting.findByStartingtime", query = "SELECT m FROM Meeting m WHERE m.startingtime = :startingtime"),
    @NamedQuery(name = "Meeting.findByDuration", query = "SELECT m FROM Meeting m WHERE m.duration = :duration")})
public class Meeting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "topic")
    private String topic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startingtime")
    @Temporal(TemporalType.TIME)
    private Date startingtime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration")
    private short duration;

    public Meeting() {
    }

    public Meeting(Short id) {
        this.id = id;
    }

    public Meeting(Short id, String topic, Date startingtime, short duration) {
        this.id = id;
        this.topic = topic;
        this.startingtime = startingtime;
        this.duration = duration;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getStartingtime() {
        return startingtime;
    }

    public void setStartingtime(Date startingtime) {
        this.startingtime = startingtime;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meeting)) {
            return false;
        }
        Meeting other = (Meeting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ctm.meetingschedueling.Meeting[ id=" + id + " ]";
    }
    
}
