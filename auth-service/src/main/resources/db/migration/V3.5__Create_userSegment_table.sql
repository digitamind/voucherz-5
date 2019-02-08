CREATE TABLE CustomerSegment(
	[customerId] [bigint] NOT NULL FOREIGN KEY REFERENCES [Customer](id),
  [segmentId] [bigint] NOT NULL FOREIGN KEY REFERENCES Segment(id),
  CONSTRAINT [PK_CustomerSegment] PRIMARY KEY CLUSTERED
  (
  [customerId] ASC,
  [segmentId] ASC
  )
)