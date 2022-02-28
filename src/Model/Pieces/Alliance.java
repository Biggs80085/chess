package Model.Pieces;

public enum Alliance {
	BLACK
 {
		@Override
		public int getDirection() {
			// TODO Auto-generated method stub
			return 1;
		}
	},	
	WHITE
	
 {
		@Override
		public int getDirection() {
			// TODO Auto-generated method stub
			return -1;
		}
	};
	public abstract int getDirection();
}
