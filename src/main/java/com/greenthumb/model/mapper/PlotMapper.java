package com.greenthumb.model.mapper;

import com.greenthumb.model.dto.PlotDTO;
import com.greenthumb.model.entity.Plot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlotMapper {

    PlotMapper INSTANCE = Mappers.getMapper(PlotMapper.class);

    Plot toPlot(PlotDTO plotDTO);
    PlotDTO toPlotDTO(Plot plot);
}
