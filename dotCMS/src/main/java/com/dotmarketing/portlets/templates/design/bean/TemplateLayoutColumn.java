package com.dotmarketing.portlets.templates.design.bean;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

import static com.dotcms.util.CollectionsUtils.map;

/**
 * Created by Jonathan Gamba
 * Date: 10/25/12
 */
public class TemplateLayoutColumn extends ContainerHolder implements Serializable {

    private Integer widthPercent;
    private Integer width;
    private int leftOffset = -1;

    private static final Map<Integer, Integer> mapWithWidthPercent = ImmutableMap.<Integer, Integer>builder().put(12,100).put(11,91).put(10,83).put(9,75).put(8,66).put(7,58).put(6,50).put(5,41).put(4,33).put(3,25).put(2,16).put(1,8).build();
    private static final Map<Integer, Integer> mapWidthPercentWith = map(100, 12, 75, 9, 66, 8, 50,6, 33, 4, 25,3);

    @JsonCreator
    public TemplateLayoutColumn(@JsonProperty("containers") List<ContainerUUID> containers,
                                @JsonProperty("widthPercent") final int widthPercent,
                                @JsonProperty("leftOffset") final int leftIndex) {
        super(containers);

        this.widthPercent = widthPercent;
        this.leftOffset = leftIndex;
    }

    public Integer getWidthPercent () {
        if (widthPercent == null || widthPercent == 0){
            this.widthPercent = this.mapWithWidthPercent.get(this.width);
        }

        return widthPercent;
    }

    public Integer getWidth () {

        if (width == null || width == 0) {
            this.width = this.mapWidthPercentWith.get(this.widthPercent);
        }

        return width;
    }

    public int getLeftOffset() {
        return leftOffset;
    }
    // zero based left offset
    public int getLeft() {
        return leftOffset-1;
    }
    @Override
    public String toString() {
       try {
           return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}