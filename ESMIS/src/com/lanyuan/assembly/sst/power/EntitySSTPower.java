package com.lanyuan.assembly.sst.power;

public class EntitySSTPower {
	  private Integer id;

	    private Integer SSTId;

	    private Integer layerNo;

	    private String powerId;

	    private String userId;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }
	    
	    public Integer getSSTId() {
			return SSTId;
		}

		public void setSSTId(Integer sSTId) {
			SSTId = sSTId;
		}

		public Integer getLayerNo() {
			return layerNo;
		}

		public void setLayerNo(Integer layerNo) {
			this.layerNo = layerNo;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPowerId() {
			return powerId;
		}

		public void setPowerId(String powerId) {
			this.powerId = powerId;
		}

}
