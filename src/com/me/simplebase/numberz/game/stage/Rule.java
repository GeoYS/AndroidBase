package com.me.simplebase.numberz.game.stage;

import com.me.simplebase.numberz.game.catcher.Catcher;

public enum Rule {
	
	/**
	 * LT = less than
	 */
	LT_TEN {

		@Override
		public boolean isSatisfied(Catcher catcher) {			
			return catcher.getValue() < 10;
		}

		@Override
		public String toString() {
			return "< 10";
		}
		
	},
	EVEN {

		@Override
		public boolean isSatisfied(Catcher catcher) {			
			return catcher.getValue() % 2 == 0;
		}

		@Override
		public String toString() {
			return "even";
		}
		
	};
	
	/**
	 * Checks if catcher satisfies the rule.
	 * @param catcher
	 * @return true if satisfies, false otherwise
	 */
	public abstract boolean isSatisfied(Catcher catcher);
	
	/**
	 * String representation of the rule (for output purposes).
	 */
	public abstract String toString();
}
