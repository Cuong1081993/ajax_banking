package com.example.model;

import com.example.model.dto.LocationRegionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location_region")
@Accessors(chain = true)
public class LocationRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "province_id", nullable = false)
    private String provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "district_id", nullable = false)
    private String districtId;

    @Column(name = "district_name", nullable = false)
    private String districtName;

    @Column(name = "ward_id", nullable = false)
    private String wardId;

    @Column(name = "ward_name",nullable = false)
    private String wardName;

    @Column (nullable = false)
    private String address;

    @OneToOne(mappedBy = "locationRegion")
    private Customer customer;

    public LocationRegionDTO toLocationRegionDTO(){
        return new LocationRegionDTO()
                .setId(id)
                .setProvinceId(provinceId)
                .setProvinceName(provinceName)
                .setDistrictId(districtId)
                .setDistrictName(districtName)
                .setWardId(wardId)
                .setWardName(wardName)
                .setAddress(address)
                ;
    }
}
