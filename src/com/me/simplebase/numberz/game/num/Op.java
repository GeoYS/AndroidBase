package com.me.simplebase.numberz.game.num;

import com.me.simplebase.numberz.game.catcher.Catcher;

/**
 * Operation on the catcher's value.
 * @author GeoYS_2
 *
 */
public enum Op {
	ADD_ONE {

		@Override
		public void op(Catcher catcher) {
			int newVal = catcher.getValue() + 1;
			catcher.setValue(newVal);
		}

		@Override
		public String toString() {
			return "+1";
		}
		
	},
	ADD_TWO {

		@Override
		public void op(Catcher catcher) {
			int newVal = catcher.getValue() + 2;
			catcher.setValue(newVal);
		}

		@Override
		public String toString() {
			return "+2";
		}
		
	},
	MUL_TWO {

		@Override
		public void op(Catcher catcher) {
			int newVal = catcher.getValue() * 2;
			catcher.setValue(newVal);
		}

		@Override
		public String toString() {
			return "x2";
		}
		
	},
	MIN_THREE {

		@Override
		public void op(Catcher catcher) {
			int newVal = catcher.getValue() - 3;
			catcher.setValue(newVal);
		}

		@Override
		public String toString() {
			return "-3";
		}
		
	};
	
	/**
	 * Modify the catcher's value.
	 * @param catcher
	 */
	public abstract void op(Catcher catcher);
	
	/**
	 * String rep. of the operation.
	 */
	public abstract String toString();
}
