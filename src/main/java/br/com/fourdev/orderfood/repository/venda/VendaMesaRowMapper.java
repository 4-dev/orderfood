package br.com.fourdev.orderfood.repository.venda;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fourdev.orderfood.dto.VendaMesaDTO;

public class VendaMesaRowMapper implements RowMapper<VendaMesaDTO> {

	@Override
	public VendaMesaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		VendaMesaDTO vendaMesaDto = new VendaMesaDTO();
		
		vendaMesaDto.setId(rs.getInt("id"));
		vendaMesaDto.setTotal(rs.getBigDecimal("total"));
		
		return vendaMesaDto;
	}

}
