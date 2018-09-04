package srmsMapper;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;

import srmsModel.ResourceFeatureModel;

public class ResourceFeatureMapper implements RowMapper<ResourceFeatureModel> {
	ResourceFeatureModel mapRow(ResultSet results, int row) {
		ResourceFeatureModel model = new ResourceFeatureModel();
		model.setResFeaId(results.getInt(1));
		model.setFeaId(results.getInt(2));
		model.setLocResId(results.getInt(3));
		model.setQuantity(results.getInt(4));
		model.setDescription(results.getString(5));
		
		return model;
	}
	
}
