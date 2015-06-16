package application.api;

import java.util.List;

public interface ISpielbrett extends ICollection {
	List<IField> getFields();
	IField getFieldAtIndex(int index);
	IField getResultForDraw(int toDraw, IWissensstreiter ws);
	IField perfomDraw(int toDraw, IWissensstreiter ws);
	IField getFieldOfWissensstreiter(IWissensstreiter ws);
	IField getStartFieldByPlayer(IPlayer player);
	IField getOccupiedField();
	void drawToStartFieldFromOccupiedField(IPlayer player);
	void drawToHomeBaseFromOccupiedField(IPlayer player);
	void drawToStartFieldFromHomeBase(IWissensstreiter ws);
}
