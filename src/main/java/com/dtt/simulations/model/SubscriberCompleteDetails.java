package com.dtt.simulations.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriber_complete_details")
public class SubscriberCompleteDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscriber_uid")
    private String subscriberUid;

    @Column(name = "video_url")
    private String videoUrl;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubscriberUid() { return subscriberUid; }
    public void setSubscriberUid(String subscriberUid) { this.subscriberUid = subscriberUid; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
}
