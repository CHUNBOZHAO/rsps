package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.proto.BoxBaseStationInfo;
import com.izhuixin.rsps.proto.Cell;
import com.izhuixin.rsps.service.BoxUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CellController {

    @Autowired
    private BoxUploadService boxUploadService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){

        return "hello Spring boot";
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void sendCellsInfo(){
        List<Cell> cells = new ArrayList<com.izhuixin.rsps.proto.Cell>();
        Cell cell = new Cell();
        cell.setCellid(132300141);
        cell.setLac(65202);
        cell.setMciss((short) 50);
        cells.add(cell);

        BoxBaseStationInfo cellsInfo = new BoxBaseStationInfo();
        cellsInfo.setMcc((short)460);
        cellsInfo.setMnc((byte)0);
        cellsInfo.setType((byte)1);
        cellsInfo.setCells(cells);

        boxUploadService.sendBaseStation(cellsInfo);

    }
}
