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
	void drawToStartFieldFromOccupiedField(IWissensstreiter ws);
	void drawToHomeBaseFromOccupiedField(IWissensstreiter ws);
	void drawToStartFieldFromHomeBase(IWissensstreiter ws);
}
