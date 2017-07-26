package br.com.fourdev.orderfood.repository.venda;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fourdev.orderfood.dto.VendaMesDTO;

public class VendaMesRowMapper implements RowMapper<VendaMesDTO> {

	@Override
	public VendaMesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		VendaMesDTO vendaMesDto = new VendaMesDTO();
		
		vendaMesDto.setMes(rs.getString("mes"));
		vendaMesDto.setTotal(rs.getInt("total"));
		
		return vendaMesDto;
	}

}
