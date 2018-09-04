package srmsMapper;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;

import srmsModel.FeatureModel;

public class FeatureMapper implements RowMapper<FeatureModel> {
	FeatureModel mapRow(ResultSet results, int row) {
		FeatureModel model = new FeatureModel();
		model.setFeaId(results.getInt(1));
		model.setFeaType(results.getString(2));
		model.setDescription(results.getString(3));
		model.setIconPath(results.getString(4));
		
		return model;
	}
}
