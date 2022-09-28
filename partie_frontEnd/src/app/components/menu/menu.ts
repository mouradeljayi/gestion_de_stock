export interface Menu {
  id?: string;
  title?: string;
  icon?: string;
  url?: string;
  active?: boolean;
  sousMenu?: Array<Menu>;
}
